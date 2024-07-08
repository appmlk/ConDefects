N,M = map(int,input().split())
S = input()
T = input()

if(S==T):
    print(0)
else:
    atama = T.find(S) == 0
    sippo = T.find(S) == len(T) - len(S)
    if(atama and sippo):
        print(0)
    elif(atama):
        print(1)
    elif(sippo):
        print(2)
    else:
        print(3)
        
    