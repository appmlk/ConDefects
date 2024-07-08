x,y = map(int, input().split())

if y-x <= -3 or y-x > 3:
    print("Yes")
else:
    print("No")