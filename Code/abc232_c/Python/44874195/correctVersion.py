
def I(m):
    M = [[0]*2 for _ in range(m)]
    for i in range(m):
        x, y = map(int, input().split())
        M[i][0], M[i][1] = x-1, y-1
    return M

import itertools

n,m = map(int,input().split())

if m==0:
    print('Yes')
    exit()


Takahashi, Aoki = I(m), I(m)


l = itertools.permutations(list(range(n)))

Takahashi.sort()

for i in l:
    # print(i)
    test = [[0]*2 for _ in range(m)]
    for j in range(m):
        for k in range(2):
            test[j][k] = i[Aoki[j][k]]

        test[j].sort()

    test.sort()
    # print(Takahashi, test)

    if Takahashi == test:
        print('Yes')
        exit()

print('No')