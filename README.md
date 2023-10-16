# ConDefects Dataset and Toolkit

- **ConDefects**: A specialized dataset and toolkit curated from student submissions on the AtCoder platform. It's engineered to push the boundaries of **Fault Localization** and **Program Repair**, while also addressing the issue of data leakage that could distort the evaluation of various approaches.

- **Data Leakage Concerns**: Particularly relevant for Large Language Models like ChatGPT, data leakage poses a risk as their training could incorporate information beyond the creation of many existing datasets. ConDefects mitigates this with a **unique time-filtering feature** for more accurate assessments.

- **Labeled Code Snippets**: ConDefects is more than just a collection of faulty code. It provides labeled snippets that detail the exact **fault position** and its corrected version, enriching its value for fault localization and program repair research.

- **Additional Utilities**: The toolkit is equipped with features for **running tests** and **collecting coverage information**, adding another layer of utility for research.




## Dataset Checkout
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
* **Time Span** (`-t`): Filters tasks by their start and end dates. The dates should be in the format `YYYY-MM-DD`.
* **Difficulty Level** (`-d`): Filters tasks by their difficulty levels. Enter two integers to specify the lower and upper bounds.
* **Specific Task** (`-s`): Filters tasks by task name.

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
