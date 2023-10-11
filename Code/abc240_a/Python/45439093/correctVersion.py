a,b = map(int,input().split())
if abs(a % 10 - b % 10) in [1,9]:
    print("Yes")
else:
    print("No")