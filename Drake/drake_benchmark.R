required.packages <- c("ggplot2", "dplyr", "drake","microbenchmark")
uninstalled.packages <- setdiff(packages, rownames(installed.packages()))
if(length(uninstalled.packages) > 0) install.packages(uninstalled.packages)

library("drake")
library("microbenchmark")
library("ggplot2")

create_null <- function() {
  NULL
}

### ONLY ONE NULL TASK IN DRAKE

single_task_plan <- drake_plan(
  data = NULL,
  task = target(create_null()),
  result = target(c(task))
)

single_task_execute <- function(){
  make(single_task_plan, jobs = 1)
  readd(result)  
}

### TWO NULL TASK IN DRAKE

two_task_plan <- drake_plan(
  data = NULL,
  task1 = target(create_null()),
  task2 = target(create_null()),
  result = target(c(task1,task2))
)

two_task_execute <- function(){
  make(two_task_plan, jobs = 1)
  readd(result)
}

### TWO NULL TASK IN DRAKE (WITH PARALLELISM)

two_task_execute_parallel <- function(){
  make( two_task_plan, parallelism = "future", 
        jobs = (parallel::detectCores()-1) )
  readd(result)
}

benchmark <- microbenchmark(single_task_execute,two_task_execute,
                            two_task_execute_parallel, times = 100000)
benchmark
autoplot(benchmark)


