n,m = map(int,input().split())
if m == 0:
    print("Takahashi" if n % 2 else "Aoki")
    exit()
li = [list(map(int,input().split())) for _ in range(m)]
sg = (n - li[-1][0])^(li[0][0] - 1)
for i in range(m - 1):
    sg ^= int(li[i + 1][1] == li[i][1])
print('Takahashi' if sg else 'Aoki')