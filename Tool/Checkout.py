import os
import pickle
import shutil

def checkout(destination_directory, time, difficulty, contest, taskR, language, cwd):
    srcPath = os.path.join(cwd, "Code")
    testPath = os.path.join(cwd, "Test")
    taskList=os.listdir(srcPath)
    # judge if the destination directory exists
    if not os.path.exists(destination_directory):
        os.makedirs(destination_directory)
    # judge if the time is valid
    if time != None:
        time1=time[0]
        time2=time[1]
        if time1>time2:
            print("Invalid time range.")
            return
        datePath=os.path.join(cwd,"Tool","date")
        with open(datePath,"rb") as f:
            date=pickle.load(f)
        for task in reversed(taskList):
            thisTime=date[task]
            #compare time string YYYY-MM-DD
            if thisTime<time1 or thisTime>time2:
                taskList.remove(task)
    # judge if the difficulty is valid
    if difficulty != None:
        diff1=int(difficulty[0])
        diff2=int(difficulty[1])
        if diff1>diff2:
            print("Invalid difficulty range.")
            return
        diffPath=os.path.join(cwd,"Tool","difficulty")
        with open(diffPath,"rb") as f:
            diff=pickle.load(f)
        for task in reversed(taskList):
            thisDiff=int(diff[task])
            if thisDiff<diff1 or thisDiff>diff2:
                taskList.remove(task)
    # judge if the contest is valid
    if contest != None:
        for task in reversed(taskList):
            if contest not in task:
                taskList.remove(task)
    # judge if the task is valid
    if taskR != None:
        for task in reversed(taskList):
            if taskR != task:
                taskList.remove(task)

    for task in taskList:
        taskPath=os.path.join(srcPath,task)
        taskDestPath=os.path.join(destination_directory,task)
        innerPath=os.listdir(taskPath)
        for inner in innerPath:
            ineerPath=os.path.join(taskPath,inner)
            innerDestPath=os.path.join(taskDestPath,inner)
            if language==None or ineerPath.lower().endswith(language.lower()):
                #copy file
                shutil.copytree(ineerPath,innerDestPath)