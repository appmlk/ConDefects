M,D = map(int,input().split())
y,m,d = map(int,input().split())

if d == D:  # 最後の日
    d = 1
    if m == M:
        m = 1
        y += 1
else:
    d += 1

print(y,m,d)