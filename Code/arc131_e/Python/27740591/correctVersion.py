N=int(input())
if N%3==2 or N<=4 :
    print("No")
else:
    print("Yes")
    n=N
    R=[]
    B=[]
    W=[]
    while n>10:
        R.append(n-1)
        R.append(n-6)
        B.append(n-2)
        B.append(n-5)
        W.append(n-3)
        W.append(n-4)
        n-=6
    if n==6:
        R.append(1)
        R.append(4)
        B.append(2)
        B.append(3)
        W.append(5)
    elif n==7:
        R.append(1)
        R.append(6)
        B.append(2)
        B.append(5)
        W.append(3)
        W.append(4)
    elif n==9:
        R.append(8)
        R.append(4)
        B.append(7)
        B.append(5)
        W.append(1)
        W.append(2)
        W.append(3)
        W.append(6)
    elif n==10:
        R.append(9)
        R.append(6)
        B.append(8)
        B.append(7)
        W.append(1)
        W.append(2)
        W.append(3)
        W.append(4)
        W.append(5)
    for i in reversed(range(1,N)):
        if i in R:
            print("R"*i)
        elif i in B:
            print("B"*i)
        else:
            print("W"*i)