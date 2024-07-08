def check(i, total):
    pos = (H//pow(2,i)) * (W//pow(2,i)) * pow(2,i*2)
    if pos >= total:
        return True
    else:
        return False

H, W, N = map(int,input().split())
A = list(map(int,input().split()))
cnt = [0] * 26
for a in A:
    cnt[a] += 1
total = 0
for i in range(25, -1, -1):
    total += cnt[i] * pow(2, i*2)
    if not check(i, total):
        print("No")
        exit()
print("Yes")