import sys
array = []
for i in sys.stdin.readlines():
    array.append(int(i.rstrip()))
array.sort()
for i in array:
    print(i)