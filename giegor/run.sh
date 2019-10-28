export repetitions=1000
export size=250
export ids=30
export iterations=10
java -cp target/giegor-0.1-SNAPSHOT.jar giegor.workload.ExecuteArrayList > al_"$size"_"$ids"_"$iterations"_"$repetitions"_"$otherListParts"_"$i".txt
java -cp target/giegor-0.1-SNAPSHOT.jar giegor.workload.ExecuteVector > v_"$size"_"$ids"_"$iterations"_"$repetitions"_"$otherListParts"_"$i".txt

