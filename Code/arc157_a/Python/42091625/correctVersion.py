n,a,b,c,d = map(int, input().split())
print("No" if abs(b - c) > 1 or (b == c == 0 and (a != 0 and d != 0)) else "Yes")