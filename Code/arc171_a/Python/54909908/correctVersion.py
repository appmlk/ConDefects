t=int(input())
for おはよう in range(t):
    n,a,b=map(int,input().split())
    if n<a:
        print("No")
        continue
    if n%2==1:
      N=n
    else:
      N=n
    if min(n-a,(N+1)//2)*(n-a)>=b:
        print("Yes")
    else:
        print("No")