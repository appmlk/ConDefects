A,B=map(int, input().split())
if A%3==0 and B==A+1:
    print('No')
elif A==B-1:
    print('Yes')
else:
    print('No')