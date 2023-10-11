def recur(n):
    x=0
    for i in range(28,-1,-1):
        p=pow(3,i)
        x += n // p
        n-=p*(n//p)

    return x




def solve():
    n,k=map(int,input().split())
    x=recur(n)
    if x>k or k%2!=n%2:
        print("No")
    else:
        print("Yes")
    

for i in range(int(input())):
    solve()