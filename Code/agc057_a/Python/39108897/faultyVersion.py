from sys import stdin
T = int(input())
for i in range(T):
    L,R = [x for x in stdin.readline().rstrip().split()]
    x = len(L)
    y = len(R)
    if(x == y):
        print(int(R)-int(L)+1)
    elif(R[0]!="1"):
        print(int(R)-10**(y-1)+1)
    else:
        flag = True
        j = 0
        while j+1 < y:
            if(R[j+1]!="0" and R[j+1]!="1"):
                print(min(int(R)-int(L)+1,10**(y-1)))
                flag = False
                break
            elif(R[j+1]!="1"):
                print(min(int(R)-int(L)+1,int(R)-int(R)//10))
                flag = False
                break
            j += 1
        if(flag):
            print(print(min(int(R)-int(L)+1,int(R)-int(R)//10)))
        
