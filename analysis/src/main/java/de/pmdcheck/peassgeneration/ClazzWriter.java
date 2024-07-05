package de.pmdcheck.peassgeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ReferenceType;

import de.dagere.nodeDiffDetector.utils.JavaParserProvider;
import de.dagere.peass.testtransformation.ParseUtil;

public class ClazzWriter {

   private final ClassOrInterfaceDeclaration declaration;
   private final CompilationUnit unit;
   private final String methodName;

   public ClazzWriter(File benchmarkFile, String methodName) throws FileNotFoundException {
      unit = JavaParserProvider.parse(benchmarkFile);
      declaration = ParseUtil.getClasses(unit).get(0);
      this.methodName = methodName;
   }

   public void writeClazz(String clazzName, File testFile) throws FileNotFoundException, IOException {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
         writer.write("package de.peass;\n\n");

         writeImports(unit, writer);

         writer.write("\npublic class " + clazzName + "{\n\n");

         System.out.println(clazzName);
         writeClazzes(writer);
         writeVariables(writer);

         writeOtherMethods(writer);

         writer.write("@Test\n");
         writer.write("public void test" + clazzName + "() ");
         writeTestMethod(writer);

         writer.write(" }\n}");
         writer.flush();
      }
   }

   public void writeTestMethod(BufferedWriter writer) throws IOException {
      for (MethodDeclaration method : declaration.getMethods()) {
         if (method.getName().getIdentifier().equals(methodName)) {
            if (method.getThrownExceptions().size() > 0) {
               writer.write(" throws ");
               for (ReferenceType type : method.getThrownExceptions()) {
                  writer.write(type.toString());
               }
            }
            writer.write("{\n");
            BlockStmt block = method.getBody().get();
            for (Node statement : block.getChildNodes()) {
               if (!(statement instanceof ReturnStmt)) {
                  writer.write("   " + statement.toString() + "\n");
               } else {
                  ReturnStmt statement2 = (ReturnStmt) statement;
                  Expression expression = statement2.getExpression().get();
                  writer.write("   Assert.assertNotNull(" + expression.toString() + ");\n");
               }
            }
         }
      }
   }

   public void writeImports(final CompilationUnit unit, BufferedWriter writer) throws IOException {
      writer.write("import org.junit.Test;\n");
      writer.write("import org.junit.Assert;\n");
      writer.write("import org.junit.BeforeClass;\n");
      writer.write("import org.junit.Before;\n");
      for (ImportDeclaration importDec : unit.getImports()) {
         if (!importDec.toString().contains("org.openjdk.jmh")) {
            writer.write(importDec.toString());
         }

      }
   }

   public void writeOtherMethods(BufferedWriter writer) throws IOException {
      for (MethodDeclaration method : declaration.getMethods()) {
         if (!method.getName().getIdentifier().equals("testBad") &&
               !method.getName().getIdentifier().equals("testGood") &&
               !method.getName().getIdentifier().equals("baseline")) {
            if (method.getAnnotations().size() != 0) {
               method.getAnnotations().clear();
               if (method.getModifiers().contains(Modifier.staticModifier())) {
                  writer.write("@BeforeClass\n");
               } else {
                  writer.write("@Before\n");
               }
               writer.write(method.toString() + "\n");
            } else {
               writer.write(method.toString() + "\n");
            }
         }
      }
   }

   public void writeVariables(BufferedWriter writer) throws IOException {
      for (Node child : declaration.getChildNodes()) {
         System.out.println(child + " " + child.getClass());
         if (child instanceof FieldDeclaration) {
            writer.write(child.toString() + "\n\n");
         }
      }
   }

   public void writeClazzes(BufferedWriter writer) throws IOException {
      List<ClassOrInterfaceDeclaration> childClasses = declaration.findAll(ClassOrInterfaceDeclaration.class);
      childClasses.remove(declaration);
      for (ClassOrInterfaceDeclaration child : childClasses) {
         writer.write(child.toString() + "\n\n");
      }
   }
}
