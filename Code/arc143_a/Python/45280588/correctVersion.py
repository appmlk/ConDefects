A,B,C=map(int,input().split())
max_A=max(A,B,C)
sum_B=A+B+C
if sum_B-max_A>=max_A:
    print(max_A)
else:
    print(-1)