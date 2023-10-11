

def fprint(arys):
    for i in range(len(arys)):
        print(*arys[i])
    print("-"*len(arys)*5)
    return

h, w = map(int, input().split())
tableA = [[] for i in range(h)]
tableB = [[] for i in range(h)]
mp1 = [[""]*w for i in range(h)]
mp2 = [[""]*w for i in range(h)]
for i in range(h):
    s = list(input())
    tableA[i].extend(s)
for i in range(h):
    s = list(input())
    tableB[i].extend(s)

for dy in range(h):
    for dx in range(w):
        for i in range(h):
            for j in range(w):
                k = (j-dx)%w
                mp1[i][k] = tableA[i][j]
        for j in range(w):
            for i in range(h):
                k = (i-dy)%h
                mp2[k][j] = mp1[i][j]
        #print(dy, dx)
        #fprint(mp2)
        if mp2 == tableB:
            print("Yes")
            exit()
else:
    print("No")



