
ans = "Yes"

h,w,n = map(int,input().split())
a = list(map(int,input().split()))
a.sort()
a.reverse()
l =[[h,w]]

for i in range(n):
    x = 2**a[i]
    y = 2**a[i]


    m = len(l)
    for j in range(m):
        s = l[j][0]
        t = l[j][1]

        f = 0
        if x <= s and y <= t:
            l.pop(j)
            w1 = x
            h1 = t - y

            if 0 < h1:
                p = [w1,h1]
                p.sort()
                l.append(p)

            w2 = s - x
            h2 = t

            if 0 < w2:
                q = [w2,h2]
                q.sort()
                l.append(q)

            l.sort()
            f = 1

            break

    if f == 0:
        ans = "No"


print(ans)
