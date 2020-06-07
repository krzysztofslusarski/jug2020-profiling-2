Source code from my lecture: TODO

##Used software
###Perf-map-agent + jmaps.sh
https://github.com/jvm-profiling-tools/perf-map-agent
https://github.com/brendangregg/Misc/blob/master/java/jmaps
###FlameGraph
https://github.com/brendangregg/FlameGraph
###Async-profiler
https://github.com/jvm-profiling-tools/async-profiler
##Used commands

###Collapsed stack:
```shell script
cat collapsed-bigger.txt  | ~/Aplikacje/FlameGraph/flamegraph.pl > ~/Aplikacje/out.svg
cat collapsed-bigger.txt  | ~/Aplikacje/FlameGraph/flamegraph.pl --color java --hash  > ~/Aplikacje/out.svg
cat collapsed-bigger.txt  | ~/Aplikacje/FlameGraph/flamegraph.pl --color java --hash  --reverse --inverted > ~/Aplikacje/out.svg
```

###CPU:
```shell script
AsyncProfiler/profiler.sh -t -e cpu -o collapsed -f async.txt -d 10 `pgrep -f Demo`
FlameGraph/flamegraph.pl --hash --color java async.txt  > out.svg
sudo perf record -F 97 -ag -- sleep 10; sudo ./jmaps.sh -u
sudo perf script | FlameGraph/stackcollapse-perf.pl  --kernel --tid > perf.txt
FlameGraph/flamegraph.pl --hash --color java perf.txt  > out.svg
```

###False sharing:
```shell script
AsyncProfiler/profiler.sh -e L1-dcache-load-misses -o collapsed -f async.txt -d 10 `pgrep -f Demo`
sudo perf c2c record -g -p `pgrep -f Demo` -- sleep 10; sudo ./jmaps.sh
sudo perf c2c report --full-symbols
```

###Method:
```shell script
AsyncProfiler/profiler.sh -e java/lang/System.gc -o collapsed -f async.txt -d 10 `pgrep -f Demo`
AsyncProfiler/profiler.sh -e java/lang/Runtime.gc -o collapsed -f async.txt -d 10 `pgrep -f Demo`
AsyncProfiler/profiler.sh -e Java_java_lang_Runtime_gc -o collapsed -f async.txt -d 10 `pgrep -f Demo`
```

###Memory:
```shell script
AsyncProfiler/profiler.sh -e alloc -o collapsed -f async.txt -d 10 `pgrep -f Demo`
AsyncProfiler/profiler.sh -e page-faults -o collapsed -f async.txt -d 10 `pgrep -f OffHeap`
curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel":"TRACE", "effectiveLevel":"TRACE"}' http://localhost:8080/actuator/loggers/pl.ks.profiling.demo.RequestLoggingFilter
curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel":"TRACE", "effectiveLevel":"TRACE"}' http://localhost:8080/actuator/loggers/org.springframework.web.filter.CommonsRequestLoggingFilter
```

