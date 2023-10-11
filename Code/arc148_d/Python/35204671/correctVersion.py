N,M=map(int, input().split())
array=list(map(int, input().split()))
sss=set()
for a in array:
    if a in sss:
        sss.remove(a)
    else:
        sss.add(a)
if M%2==1:
    if sss:
        print("Alice")
    else:
        print("Bob")
else:
    if len(sss)%4!=0:
        print("Alice")
        exit()
    for s in sss:
        if (s+M//2)%M not in sss:
            print("Alice")
            exit()
    print("Bob")
