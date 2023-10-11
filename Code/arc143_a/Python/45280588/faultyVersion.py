A,B,C=map(int,input().split())
max_A=max(A,B,C)
sum_B=A+B+C
if sum_B-max_A>=max_A:
    print(sum_B//2)
else:
    print(-1)