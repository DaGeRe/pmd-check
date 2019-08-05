cat classes.txt | while read line
do 
	printf "package de.peass;\n\nimport org.openjdk.jmh.annotations.Benchmark;\n\npublic class "$line"Benchmark {\n   @Benchmark\n   public void testGood() {\n   }\n\n   @Benchmark\n   public void testBad() {\n   }\n}" > $line"Benchmark.java"
done
