#まずはリストもmatrixに起こす
#h,w = map(int, input().split())
#mtx = []
#for i in range(h+2):
#    tmp = [0] * (w + 2)
#    if i != 0 and i != h + 1:
#        s = input()
#        for j in range(1, w+1):
#            if s[j-1] == "#":
#                tmp[j] = -1
#    mtx.append(tmp)
#print(mtx)

#cnt = 2
#same = set()
#for i in range(1,h+1):
#    for j in range(1,w+1):
#        box9 = [mtx[i+x][j+y] for x in range(-1,2) for y in range(-1,2)]
#        target = mtx[i][j]
#        boxmax = max(box9)
#        if target == 0:
#            continue
#        elif target >= 1:
#            if boxmax == 1:
#                for x in range(-1, 2):
#                    for y in range(-1,2):
#                        if mtx[i+x][j+y] == 1:
#                            mtx[i+x][j+y] = cnt
#                cnt += 1
#            elif boxmax >= 2:
#                for x in range(-1, 2):
#                    for y in range(-1,2):
#                        if mtx[i+x][j+y] == 0:
#                            continue
#                        elif mtx[i+x][j+y] == 1:
#                            mtx[i+x][j+y] = boxmax
#                        elif mtx[i+x][j+y] != boxmax:
#                            same.add((mtx[i+x][j+y],boxmax))
#                            mtx[i+x][j+y] = boxmax
#                        elif mtx[i+x][j+y] == boxmax:
#                            continue


#lstsame = list(same)
#lstsame.sort()
#print(lstsame)
#lstn = [i for i in range(2, cnt)]

#for i in range(h+2):
#    print("".join(map(str, mtx[i])))
    
#for i in range(len(lstsame)):
#    a,b = lstsame[i][0], lstsame[i][1]
#    for j in range(len(lstn)):
#        if lstn[j] == a:
#            lstn[j] = b
#print(lstn)
#print(len(set(lstn)))
#結構頑張ったしサンプルデータは通ったがテストデータでWAだった。どこが問題かは分からなかった。
#sameセットの生成、および統合に問題があったんじゃないかとChatGPTには言われた。

#mtxはだいたい生かしてUnion-find木でAC獲るぞ
h,w = map(int, input().split())
mtx = []
rank = [1] * w * h
for i in range(h):
    tmp = [-2] * w
    s = input()
    for j in range(w):
        if s[j] == "#":
            tmp[j] = -1
    mtx=mtx+tmp

def find(x):
    global mtx
    if mtx[x] == -1:
        return(x)
    elif mtx[x] == -2:
        return(-2)
    else:
        mtx[x] = find(mtx[x])
        return mtx[x]

def union(x,y):
    global mtx,rank
    x = find(x)
    y = find(y)
    if x == y:
        return
    else:
        if rank[x] > rank[y]:
            x,y = y,x
        elif rank[x] == rank[y]:
            rank[y] += 1
        mtx[x] = y

for i in range(h):
    for j in range(w):
        direction=[(-1,-1), (-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]
        if mtx[i*w+j] != -1:
            continue
        for a,b in direction:
            if  0 <= i+a < h and 0 <= j+b < w:
                if mtx[(i+a)*w+(j+b)] != -2:
                    union(i*w+j,(i+a)*w+(j+b))

#print(mtx)
answer = set()
for i in range(h):
    for j in range(w):
        if mtx[i*w+j] != -2:
            answer.add(find(i*w+j))

print(len(answer))