# ConDefects Dataset and Toolkit

With the growing interest on Large Language Models (LLMs) for fault localization and program repair, ensuring the integrity and generalizability of the LLM-based methods becomes paramount. The code in existing widely-adopted benchmarks for these tasks was written before the the bloom of LLMs and may be included in the training data of existing popular LLMs, thereby suffering from the threat of data leakage, leading to misleadingly optimistic performance metrics. To address this issue, we introduce "ConDefects", a novel dataset of real faults meticulously curated to eliminate such overlap. 

ConDefects contains 1,254 Java faulty programs and 1,625 Python faulty programs.
All these programs are sourced from the online competition platform AtCoder and were produced between October 2021 and September 2023.

We pair each fault with fault locations and the corresponding repaired code versions, making it tailored for in fault localization and program repair related research.
We also provide interfaces for selecting subsets based on different time windows and coding task difficulties.
While inspired by LLM-based tasks, ConDefects can be adopted for benchmarking ALL types of  fault localization and program repair methods.
The dataset is publicly available, and a demo video can be found at https://www.youtube.com/watch?v=22j15Hj5ONk.


## Test Cases Download
Since the root directory contains a large `Test.zip` file, multiple download options are provided for your convenience.
- **MEGA Drive**: [Download from MEGA](https://mega.nz/file/qnohWRhY#L7qgO7C3qqsXyQqJzLuPUJy06dEmnff9J1nUGYNPAHQ)
- **OneDrive**: [Download from OneDrive](https://1drv.ms/u/s!Auo_FVX2RDMxn5MJgml8-0clWIMQSw?e=1gfISm)
- **Baidu Drive**: Link: https://pan.baidu.com/s/188qz6TttRN6ZTn0XypkmHw?pwd=bbjg, Extraction Code: bbjg

## Additional Files
The root directory also contains two readable files, `date.txt` and `difficulty.txt`, which provide information about the commencement date and difficulty level of all tasks, respectively.

## Environment Requirements
- Install coverage 4.5 version as higher versions may cause issues in parsing coverage information.
  ```bash
  pip install coverage==4.5
- Ensure that Java and Python runtimes are properly installed and configured.

# Toolkit Usage Guide for ConDefects

To engage with the ConDefects dataset, our toolkit provides a centralized script entry point named `ConDefects.py`. Below is a guide detailing its various features and how to utilize them.

## Meta Information

To access a comprehensive range of metadata about the ConDefects dataset, use the following standardized command:

```bash
python3 ConDefects.py info [options]
```

The `info` command encompasses multiple options for querying specific elements of the dataset:


### Output Tasks

Getting a list of all available tasks:

```bash
python3 ConDefects.py info --list-tasks
```


### Output Test Cases

Getting a list of all test cases available for a specific task, specify the task name:

```bash
python3 ConDefects.py info --test-cases --task <task_name>
```

### Output Programs

Getting a list of all programs available as well as their ID for a specific task, specify the task name along with the language (`java` or `python`):

```bash
python3 ConDefects.py info --programs --task <task_name> --language <language>
```

### Output Program Fault Details

Getting details about the fault position, content of faulty statement, and the corresponding corrected statement, specify the program ID:

```bash
python3 ConDefects.py info --program-id <program_ID>
```

## Dataset Checkout

To checkout users' needed dataset, use the following command format:

```bash
python3 ConDefects.py checkout -w <dest_dir> [options]
```

### Parameters

* **Destination Directory** (`-w`): Specifies the directory where the checked-out dataset will be stored.
* **Language** (`-l`): Specifies the programming language (`java` or `python`).
* **Time Span** (`-t`): Selects coding-tasks by their start and end dates. The dates should be in the format `YYYY-MM-DD`. It's important to note that the end date should be greater than start date, and both dates should fall within our current dataset's time range, which is from 2021-10-01 to 2023-09-30.
* **Difficulty Level** (`-d`): Selects coding-tasks by their difficulty levels. Enter two integers to specify the lower and upper bounds.
* **Specific Task** (`-s`): Selects coding-tasks by a certain coding-task name.

## Execution and Test Case Report

To execute tasks and generate test case reports, use the following command format:

```bash
python3 ConDefects.py run -w <dest_dir> [options]
```

### Specific Task

To execute a specific task within the directory, use the `-s` or `--task` option followed by the task name:

```bash
python3 ConDefects.py run -w <dest_dir> -s <task_name>
```

### Test Case Filter

To run only specific test cases for a given task, use the `-t` or `--test` option followed by the test case identifiers. Note that this option must be used in conjunction with the `-s` or `--task` option:

```bash
python3 ConDefects.py run -w <dest_dir> -s <task_name> -t <test_case_Name1> [<test_case_name2> ...]
```

## Coverage Collection

To collect coverage information, use the following command format:

```bash
python3 ConDefects.py coverage -w <dest_dir> -o <output_dir> [options]
```

* `-o <output_dir>`: Specifies the directory where the coverage information will be stored.

- **Output Files**: The toolkit generates the following output files for each program:
  - `covMatrix.txt`: Contains statement coverage information, where each line represents the coverage for a specific test case.
  - `results.txt`: Holds the test results for each test case, indicating whether the test passed (`True`) or failed (`False`).
  - `testList.txt`: Lists the order of test case execution, correlating to the lines in `covMatrix.txt` and `results.txt`.
