import os
import pickle


def checkInfo(list_contests,list_tasks, contest, task, programs, test_cases, program_id, language, cwd):
    srcPath = os.path.join(cwd,"Code")
    testPath = os.path.join(cwd,"Test")
    if list_contests==True:
        dirList = os.listdir(srcPath)
        dirNameList = []
        for dirName in dirList:
            dirNameList.append(dirName)

        contestList=[]
        for dirName in dirNameList:
            if os.path.isdir(os.path.join(srcPath, dirName)):
                # get contest name from a dir
                problemName = dirName.split('_')[0]
                if problemName not in contestList:
                    contestList.append(problemName)
                    print(problemName)
    elif contest!=None:
        dirList = os.listdir(srcPath)
        dirNameList = []
        for dirName in dirList:
            dirNameList.append(dirName)
        print("tasks in " + contest + ":")
        for dirName in dirNameList:
            if os.path.isdir(os.path.join(srcPath, dirName)):
                # get contest name from a dir
                problemName = dirName.split('_')[0]
                if problemName.lower() == str(contest).lower():
                    print(dirName.split('_')[0]+"_"+dirName.split('_')[1])
    elif list_tasks==True:
        dirList = os.listdir(srcPath)
        dirNameList = []
        for dirName in dirList:
            dirNameList.append(dirName)
            print(dirName)
    elif task!=None:
        #check language
        if programs==True:
            if language==None:
                print("Please specify a language. [java, python]")
                return
            taskPath=os.path.join(srcPath,task,language)
            if not os.path.exists(taskPath):
                print("No code. Please checkout the task name or language.")
            else:
                print("Code in "+task+":")
                codeList=os.listdir(taskPath)
                for code in codeList:
                    print(code,"Path:",os.path.join(taskPath,code))
        elif test_cases==True:
            taskPath=os.path.join(testPath,task.split('_')[0],task.split('_')[1],"in")
            if not os.path.exists(taskPath):
                print("No test cases. Please checkout the task name.")
            else:
                print("Test cases in "+task+":")
                testList=os.listdir(taskPath)
                for test in testList:
                    print(test)
        else:
            print("Please specify a type. [programs, test-cases]")
    elif program_id!=None:
        path2CodePath=os.path.join(cwd,"Tool","path2Code")
        with open(path2CodePath,"rb") as f:
            path2Code=pickle.load(f)
        if program_id not in path2Code:
            print("No such program id. Please checkout the program id.")
        else:
            codePath=path2Code[program_id]
            codeRealPath=cwd+codePath
            print("Code path:",codeRealPath)
            fileList=os.listdir(codeRealPath)
            for file in fileList:
                if file.endswith(".java"):
                    faultyVersionPath = os.path.join(codeRealPath, "faultyVersion.java")
                    correctVersionPath = os.path.join(codeRealPath, "correctVersion.java")
                    break
                elif file.endswith(".py"):
                    faultyVersionPath = os.path.join(codeRealPath, "faultyVersion.py")
                    correctVersionPath = os.path.join(codeRealPath, "correctVersion.py")
                    break
            faultLocationPath=os.path.join(codeRealPath,"faultLocation.txt")
            with open(faultLocationPath,"r") as f:
                faultLocation=f.readline()
            faultLocationInt=int(faultLocation)-1
            print("Fault location:",faultLocationInt)
            with open(faultyVersionPath,"r",encoding="utf-8") as f:
                faultyLines=f.readlines()
            with open(correctVersionPath,"r",encoding="utf-8") as f:
                correctLines=f.readlines()
            print("Faulty Line:",faultyLines[faultLocationInt].strip())
            print("Correct Line:",correctLines[faultLocationInt].strip())