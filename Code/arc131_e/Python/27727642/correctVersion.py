n = int(input())
if(n<=4):
    print("No")
    exit()
if(n*(n-1)%3!=0):
    print("No")
    exit()
if(n*(n-1)%3!=0):
    print("No")
    exit()

ans = [["."]*n for _ in range(n)]

R = []
B = []
W = []
def divide(n):
    global R,B,W
    if(n>10):
        R += [n-1,n-6]
        B += [n-2,n-5]
        W += [n-3,n-4]
        divide(n-6)
    else:
        if(n==6):
            R += [1,4]
            B += [2,3]
            W += [5]
        elif(n==7):
            R += [1,6]
            B += [2,5]
            W += [3,4]
        elif(n==9):
            R += [1,2,3,6]
            B += [4,8]
            W += [5,7]
        else:
            R += [1,2,3,4,5]
            B += [6,9]
            W += [7,8]

divide(n)
for i in range(1,n):
    for ii in range(n+1):
        if(i+ii in R):
            ans[i][n-ii-1]="R"
        elif(i+ii in B):
            ans[i][n-ii-1]="B"
        elif(i+ii in W):
            ans[i][n-ii-1]="W"
        else:
            continue

print("Yes")
for i in range(1,n):
    d = ans[i][i:]
    print("".join(d)[::-1])