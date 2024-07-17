# ConDefects Dataset and Toolkit

With the growing interest on Large Language Models (LLMs) for fault localization and program repair, ensuring the integrity and generalizability of the LLM-based methods becomes paramount. The code in existing widely-adopted benchmarks for these tasks was written before the the bloom of LLMs and may be included in the training data of existing popular LLMs, thereby suffering from the threat of data leakage, leading to misleadingly optimistic performance metrics. To address this issue, we introduce "ConDefects", a novel dataset of real faults meticulously curated to eliminate such overlap. 

ConDefects contains 1,254 Java faulty programs and 1,625 Python faulty programs.
All these programs are sourced from the online competition platform AtCoder and were produced between October 2021 and September 2023.

We pair each fault with fault locations and the corresponding repaired code versions, making it tailored for in fault localization and program repair related research.
We also provide interfaces for selecting subsets based on different time windows and coding task difficulties.
While inspired by LLM-based tasks, ConDefects can be adopted for benchmarking ALL types of  fault localization and program repair methods.
The dataset is publicly available, and a demo video can be found at https://www.youtube.com/watch?v=22j15Hj5ONk.

## Update (2024/6)
ConDefects dataset has been updated to include data up until June 2024.
The following chart illustrates the distribution of the dataset by time.
![image](https://github.com/appmlk/ConDefects/blob/main/Distribution.png)

The table below provides statistics for ConDefects, showcasing the number of coding tasks, total number of files, average lines of code, and average number of functions for both Java and Python languages.

| Feature          | Java  | Python |
| ---------------- | ----- | ------ |
| **# Task**       | 810   | 985    |
| **# Files**      | 2045  | 2864   |
| **Average LOC**  | 259.22 | 49.03  |
| **Average # Func.** | 22.45 | 2.91  |

\# Task: Number of coding tasks

\# Files: Total number of files

Average LOC: Average lines of code

Average # Func.: Average number of functions

## Test Cases Download
Since the root directory contains a large `Test.zip` file, multiple download options are provided for your convenience.
- **OneDrive**: [Download from OneDrive](https://1drv.ms/u/s!Auo_FVX2RDMxn6g_jFpzEGOjScia7Q?e=lgOeyi)
- **Baidu Drive**: Link: https://pan.baidu.com/s/1YsTYWMfV3L16irItGJQlmA?pwd=hdlz 

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

## Ensuring Legal Compliance

Our data is stored on the U.S. website GitHub, and the paper citing this data is planned for publication at a conference under the American publisher ACM. Consequently, our data usage adheres to the U.S. Digital Millennium Copyright Act (DMCA).

Under DMCA §1201, "No person shall circumvent a technological measure that effectively controls access to a work protected under this title." Our data collection methods from Atcoder do not circumvent any such access controls, ensuring compliance with this statute. For detail, visit [https://www.law.cornell.edu/uscode/text/17/1201](https://www.law.cornell.edu/uscode/text/17/1201).

Moreover, the development of the ConDefects dataset is governed by Fair Use §107, which allows for "the fair use of a copyrighted work, including such use by... scholarship, or research, is not an infringement of copyright." This non-commercial use is intended solely for academic purposes and is unlikely to impact the original work’s market value. For detail, visit [https://www.law.cornell.edu/uscode/text/17/107](https://www.law.cornell.edu/uscode/text/17/107).

Additionally, our practices also comply with the legal frameworks in Japan, where Atcoder is based. Under Article 30-4 of the Japanese Copyright Law, it states: "It is permissible to exploit a work, in any way and to the extent considered necessary, in any of the following cases or other cases... if it is done for use in data analysis (meaning the extraction, comparison, classification, or other statistical analysis of the constituent language, sounds, images, or other elemental data from a large number of works or a large volume of other such data)..." Our data collection methods from Japanese websites strictly adhere to these guidelines. We ensure that our activities are conducted in a manner that is fully compliant with this statute, focusing on non-commercial, scientific research without bypassing any form of copyright protection. This alignment with the law supports both academic freedom and the rights of copyright owners. For detail, visit [https://www.cric.or.jp/english/clj/cl2.html](https://www.cric.or.jp/english/clj/cl2.html).

In Conclusion, our data storage, usage, and collection practices ensure full compliance with international copyright laws, particularly under the statutes of the United States and Japan. This thorough adherence to legal norms supports the integrity of our research and respects the copyright interests of all parties involved.

## License

The ConDefects data is licensed under CC BY-SA 4.0 license [^1], and our code is open-sourced under the MIT license [^2].

[^1]: https://creativecommons.org/share-your-work/cclicenses/
[^2]: https://opensource.org/license/mit
