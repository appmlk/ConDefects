n, q = map(int, input().split())

record = [(i, 0) for i in reversed(range(1,n+1))]
dict = {"R":(1,0), "L":(-1,0), "U":(0,1), "D":(0,-1)}
for i in range(q):
    select, part = input().split()
    if select == "1":
        x,y = record[-1]
        dx, dy = dict[part]
        record.append((x+dx, y+dy))


    else:
        part = int(part)
        print(*record[-part])