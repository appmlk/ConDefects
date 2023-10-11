from collections import defaultdict

T = int(input())
for _ in range(T):
    N = int(input())
    C = list(map(int,input().split()))

    dc = {}
    for c in C:
        if c not in dc:
            dc[c] = 0
        dc[c] += 1

    lst = []
    cnt = 0
    for k in dc:
        lst.append((dc[k],k))
        cnt += 1
    lst.sort(reverse=True)

    if N//2 < cnt-1:
        ans = []
        for i in range(cnt):
            for _ in range(lst[i][0]):
                ans.append(lst[i][1])

        print(" ".join(list(map(str,ans))))

    else:
        children = defaultdict(list)
        chs = {}
        for v in dc:
            chs[v] = dc[v] - 1
        chs[lst[0][1]] += 1
        
        j = 1
        for i in range(cnt):
            for _ in range(chs[lst[i][1]]):
                if j >= cnt:
                    break
                children[lst[i][1]].append(lst[j][1])
                j += 1
             
        def search(v):
            ans.append(v)
            dc[v] -= 1
            for w in children[v]:
                search(w)
                if dc[v] > 0:
                    ans.append(v)
                    dc[v] -= 1

        ans = []
        search(lst[0][1])

        
        tmp = []
        for n in ans:
            tmp.append(n)
            while dc[n] > 0:
                tmp.append(n)
                dc[n] -= 1

        print(" ".join(list(map(str,tmp))))