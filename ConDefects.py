import os
import sys
import zipfile

sys.path.append('./Tool')
import Tool.Info
import Tool.Checkout
import Tool.RunTest

def checkout(destination_directory, time, difficulty, contest, task, language,cwd):
    if destination_directory is None:
        print("Error: The -w parameter is required.")
        print("Use -h for help.")
        return
    Tool.Checkout.checkout(destination_directory, time, difficulty, contest, task, language,cwd)
    print(f"Checkout command called with:")
    print(f"destination_directory = {destination_directory}")
    print(f"time = {time}")
    print(f"difficulty = {difficulty}")
    print(f"contest = {contest}")
    print(f"task = {task}")
    print(f"language = {language}")


def run(destination_directory, task, test,cwd):
    if destination_directory is None:
        print("Error: The -w parameter is required.")
        print("Use -h for help.")
        return
    Tool.RunTest.runTest(destination_directory, None, task, test, cwd,False)
    print(f"Run command called with:")
    print(f"destination_directory = {destination_directory}")
    print(f"task = {task}")
    print(f"test = {test}")


def coverage(destination_directory, output_directory, task, test,cwd):
    if destination_directory is None or output_directory is None:
        print("Error: The -w and -o parameters are required.")
        print("Use -h for help.")
        return
    Tool.RunTest.runTest(destination_directory, output_directory, task, test,cwd,True)
    print(f"Coverage command called with:")
    print(f"destination_directory = {destination_directory}")
    print(f"output_directory = {output_directory}")
    print(f"task = {task}")
    print(f"test = {test}")


def info(list_contests, list_tasks, contest, task, programs, test_cases, program_id, language,cwd):
    if list_contests is None and list_tasks is None and contest is None and task is None and program_id is None:
        print("Error: At least one option must be provided.")
        print("Use -h for help.")
        return
    Tool.Info.checkInfo(list_contests,list_tasks, contest, task, programs, test_cases, program_id, language,cwd)


def main(cwd):
    import argparse

    parser = argparse.ArgumentParser(description='ConDefects command-line tool.')
    subparsers = parser.add_subparsers(dest='command')

    # checkout command
    checkout_parser = subparsers.add_parser('checkout')
    checkout_parser.add_argument('-w', '--dest-dir', required=True)
    checkout_parser.add_argument('-t', '--time', nargs=2)
    checkout_parser.add_argument('-d', '--difficulty', nargs=2, type=int)
    checkout_parser.add_argument('-c', '--contest')
    checkout_parser.add_argument('-s', '--task')
    checkout_parser.add_argument('-l', '--language', choices=['java', 'python'])

    # run command
    run_parser = subparsers.add_parser('run')
    run_parser.add_argument('-w', '--dest-dir', required=True)
    run_parser.add_argument('-s', '--task')
    run_parser.add_argument('-t', '--test', nargs='+')

    # coverage command
    coverage_parser = subparsers.add_parser('coverage')
    coverage_parser.add_argument('-w', '--dest-dir', required=True)
    coverage_parser.add_argument('-o', '--output-directory', required=True)
    coverage_parser.add_argument('-s', '--task')
    coverage_parser.add_argument('-t', '--test', nargs='+')

    # info command
    info_parser = subparsers.add_parser('info')
    info_parser.add_argument('--list-contests', action='store_true')
    info_parser.add_argument('--list-tasks', action='store_true')
    info_parser.add_argument('--contest')
    info_parser.add_argument('--task')
    info_parser.add_argument('--programs', action='store_true')
    info_parser.add_argument('--test-cases', action='store_true')
    info_parser.add_argument('--program-id')
    info_parser.add_argument('-l', '--language', choices=['java', 'python'])

    args = parser.parse_args()

    if args.command == 'checkout':
        checkout(args.dest_dir, args.time, args.difficulty, args.contest, args.task, args.language,cwd)
    elif args.command == 'run':
        run(args.dest_dir, args.task, args.test,cwd)
    elif args.command == 'coverage':
        coverage(args.dest_dir, args.output_directory, args.task, args.test,cwd)
    elif args.command == 'info':
        info(args.list_contests,args.list_tasks, args.contest, args.task, args.programs, args.test_cases, args.program_id,
             args.language,cwd)
    else:
        print("Invalid command. Use -h for help.")


if __name__ == '__main__':
    #get current working directory
    cwd = os.getcwd()
    testPath = os.path.join(cwd, 'Test')
    testZipPath = os.path.join(cwd, 'Test.zip')
    if not os.path.exists(testPath) and os.path.exists(testZipPath):
        print('Extracting Test.zip...')
        with zipfile.ZipFile(testZipPath, 'r') as zip_ref:
            zip_ref.extractall(cwd)
        print('Extracting Test.zip finished.')
    elif not os.path.exists(testPath) and not os.path.exists(testZipPath):
        print('Test.zip not found.')
        print("Don't worry! You can download it by following the guide on our GitHub repository.")
        print("Then make sure to place the downloaded Test.zip in the same directory as ConDefects.py.")
        exit()
    main(cwd)