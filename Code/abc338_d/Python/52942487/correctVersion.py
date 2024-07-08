n, m = map(int, input().split())
x = list(map(int, input().split()))

imos_l = [0] * (n + 1)

for i in range(m - 1):
    st, en = x[i], x[i + 1]
    dist1 = max(st, en) - min(st, en)
    dist2 = n - dist1
    short_dist = min(dist1, dist2)
    long_dist = max(dist1, dist2)
    dist_diff = long_dist - short_dist
    # 短い方を繋ぐ線を消すことでどれくらい距離が増えるか？
    if short_dist == dist1:
        imos_l[min(st, en) - 1] += dist_diff
        imos_l[max(st, en) - 1] -= dist_diff
    else:
        imos_l[max(st, en) - 1] += dist_diff
        imos_l[0] += dist_diff
        imos_l[min(st, en) - 1] -= dist_diff

l = [imos_l[0]]

for i in range(1, n):
    l.append(l[-1] + imos_l[i])

# print(l)

min_l = min(l)
for i in range(n):
    if l[i] == min_l:
        min_l_index = i + 1
        break

new_x = []
for i in range(m):
    if x[i] - min_l_index <= 0:
        new_x.append(x[i] - min_l_index + n)
    else:
        new_x.append(x[i] - min_l_index)

# print(new_x)

ans = 0
for i in range(m - 1):
    st, en = new_x[i], new_x[i + 1]
    ans += max(st, en) - min(st, en)

print(ans)