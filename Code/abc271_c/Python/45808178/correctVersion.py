N = int(input())
a = list(map(int, input().split()))
st = set()
for i in range(N):
    st.add(a[i])
ans = 0
s = 0
for i in range(1, N + 1):
    if i in st:
        s += 1
    else:
        s += 2
    if s > N:
        break
    ans = i
print(ans)