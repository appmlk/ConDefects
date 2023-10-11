"""
毎回hatのビット列を生成して確かめるっていうのがだめ
境目を動かしていって、境目付近を見ればいい
"""

n = int(input())
s = list(input())
w = list(map(int, input().split()))

people = []
# 正解数
# 最初、0番目の人より左にあって、全て1と予測するので1の数をカウントしておく
cnt = 0
for i in range(n):
    people.append((w[i], s[i]))
    if s[i] == '1':
        cnt += 1
people.sort()

ans = cnt
for i in range(n):  # 左から仕切りをごかす
    w, s = people[i]

    # 0の人を飛び越えたら1足す
    if s == '0':
        cnt += 1
    # 1の人を飛び越えたら1減らす
    if s == '1':
        cnt -= 1

    # 隣の人と体重が同じならそれは境界とはいえない
    # ansの更新を行わない(cntの計算だけしておいてスキップ)
    if i+1 >= n or w != people[i+1]:
        ans = max(ans, cnt)

print(ans)
