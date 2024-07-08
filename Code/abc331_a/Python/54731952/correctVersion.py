def resolve():
    M, D = map(int, input().split())
    y, m, d = map(int, input().split())
    sm, sy = 0, 0
    if d == D:
        d, sm = 1, 1
    else:
        d+=1
    if m+sm == M+1:
        m, sy = 1, 1
    else:
        m+= sm
    y += sy
    return (y, m, d)
print(*resolve())