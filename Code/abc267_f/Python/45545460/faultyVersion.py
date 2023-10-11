def dfs(s):
    time = [-1] * n
    st = [s]
    time[s] = 0
    parent = [-1] * n
    while st:
        i = st.pop()
        ti = time[i]
        for j in edges[i]:
            if time[j] > -1:
                continue
            parent[j] = i
            time[j] = ti + 1
            st.append(j)

    return time, parent


n = int(input())
edges = [[] for i in range(n)]
for i in range(n - 1):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    edges[a].append(b)
    edges[b].append(a)

time, parent = dfs(0)
s = time.index(max(time))
time, parent = dfs(s)
d = max(time)
t = time.index(d)
c = [t]
while t != s:
    t = parent[t]
    c.append(t)
rc = c.copy()
rc.reverse()

ln = len(bin(n)) - 1
left = [-1] * (n * ln)
right = [-1] * (n * ln)
for i in range(len(c) - 1):
    left[rc[i] * ln] = rc[i + 1]
    right[c[i] * ln] = c[i + 1]


for i in range(n):
    if right[i * ln] > -1:
        continue
    p = parent[i]
    left[i * ln] = p
    right[i * ln] = p


for j in range(ln - 1):
    for i in range(n):
        lij = left[i * ln + j]
        if lij > -1:
            left[i * ln + j + 1] = left[lij * ln + j]
        rij = right[i * ln + j]
        if rij > -1:
            right[i * ln + j + 1] = right[rij * ln + j]


def solve(go, u, k):
    while k:
        l = len(bin(k)) - 3
        u = go[u * ln + l]
        if u < 0:
            return -1
        k -= 1 << l
    return u + 1


q = int(input())
for i in range(q):
    u, k = map(int, input().split())
    u -= 1
    print(max(solve(left, u, k), solve(right, u, k)))
