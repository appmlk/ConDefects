import sys
input=lambda:sys.stdin.readline().rstrip()
N=int(input())
A=list(map(int,input().split()))
print(sum([abs(A[i-1]-A[i]) for i in range(N)])//2)