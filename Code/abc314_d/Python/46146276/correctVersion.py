

if __name__ == '__main__':
    N = int(input())
    S = input()
    Q = int(input())

    flg = 1
    d = dict()

    query = []
    last = 0
    for i in range(Q):
        t, x, c = input().split()
        query.append([t, x, c])
        if t == '2':
            flg = 2
            last = i
        elif t == '3':
            flg = 3
            last = i

    if flg == 2:
        S = S.lower()
    elif flg == 3:
        S = S.upper()

    for j in range(Q):
        t = query[j][0]
        x = query[j][1]
        c = query[j][2]
        if t == '1':
            x = int(x)
            if j < last:
                if flg == 1:
                    d[x-1] = c
                if flg == 2:
                    d[x-1] = c.lower()
                else:
                    d[x-1] = c.upper()
            else:
                d[x-1] = c

    for i in range(N):
        if i in d:
            print(d[i], end='')
        else:
            print(S[i], end='')





