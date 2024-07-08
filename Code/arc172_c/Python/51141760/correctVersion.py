n = int(input())
c = input()
head = c[0]
tail = c[1:]
if head == "B":
    # tail中のAとBを反転
    tail = tail.replace("A", "C").replace("B", "A").replace("C", "B")


def sign2char(n):
    if n == 0:
        return "C"
    elif n > 0:
        return "A"
    else:
        return "B"

# 初期化
count = [1]
for t in tail:
    if t == "A":
        count.append(count[-1] + 1)
    else:  # "B"
        count.append(count[-1] - 1)

ans = 1
# 先頭を1つずつ後ろにずらしていく
for i in range(n-1):
    before = count[i]
    if tail[i] != "A":
        after = before - 2
    else:
        after = before
    if sign2char(before) != sign2char(after):
        ans += 1

print(ans)