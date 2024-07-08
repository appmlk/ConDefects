n, m = map(int, input().split())
l = list(map(int, input().split()))
player = []
for i in range(n):
    player.append([input(), 0])
    
hi = [0, 0]
for i in range(n):
    score = 0
    for j, c in enumerate(player[i][0]):
        if c == 'o':
            score += l[j]
            score += 1
    player[i][1] = score
    if score >= hi[0]:
        hi = [score, i]
    
il = []
for i in range(m):
    il.append([l[i], i])
sorted_il = sorted(il)

for i in range(n):
    r = 0
    s = player[i][1]
    if s <= hi[0] and i != hi[1]:
        for j in range(m - 1, -1, -1):
            index = sorted_il[j][1]
            if player[i][0][index] == 'x' and s <= hi[0]:
                s += sorted_il[j][0]
                r += 1
    print(r)
            