def solve(k, s):
    res = 0
    lst = []
    for s_i in s:
        if lst and lst[-1][0] == s_i:lst[-1][1] += 1
        else:lst.append([s_i, 1])
    x, edge_x = [], 0
    for i in range(len(lst)):
        l_i, cnt = lst[i]
        if i in [0, len(lst) - 1] and l_i == "X":edge_x += cnt
        elif l_i == "X":x.append(cnt)
        else:res += cnt - 1

    for x_i in sorted(x):
        if k - x_i >= 0:
            k -= x_i
            res += x_i + 1
        else:
            res += k
            k -= x_i
            break
    if k > 0:
        res += k

    return res

n, k = map(int, input().split())
s = list(input())

x = s.count("X")
if n == x:
    ans = 0 if k == 0 else k - 1
elif k <= x:
    ans = solve(k, s)
else:
    """
    全部ひっくり返す->返しすぎたｎ-ｋ個をもとに戻す
    これは反転させたｓに対してｘ(x<=k)個反転させるのと同じ動作
    """
    ans = solve(n - k, ["X" if s_i == "Y" else "Y" for s_i in s])

print(ans)
