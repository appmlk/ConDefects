n=int(input())
A=0
B=10**12
for q in range(n):
    a,b=map(int,input().split())
    A=max(A,a)
    B=min(B,b)
    print(max(B-A+1,0))   