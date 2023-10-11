a, b, c = map(int, input().split())

al = [a, b, c]
al.sort()
diff = al[2] - al[1]
if a < diff:
    print(-1)
else:
    ans = 0
    # 大きいのと真ん中を合わせる
    al[0] -= diff
    al[2] -= diff
    ans += diff
    # 小さいのに合わせる
    diff = al[1] - al[0]
    ans += diff
    ans += al[0]
    print(ans)
