n,l,r = map(int,input().split())
n_l = [0]*(n.bit_length())
for i in range(n.bit_length()):
    if n >> i & 1:
        n_l[i] = 1
l_d = l.bit_length()
r_d = r.bit_length()
l_l = [0]*(l_d)
r_l = [0]*(r_d)
for i in range(l_d):
    if l >> i & 1:
        l_l[i] = 1
for i in range(r_d):
    if r >> i & 1:
        r_l[i] = 1
if n < l:
    print(0)
    exit()
ans = 0
power = [1]
for i in range(min(len(n_l),r_d)):
    power.append(2*power[-1])
for i in range(min(len(n_l),r_d)):
    if n_l[i] == 1:
        ans += min(power[i+1]-1,r)-max(power[i],l)+1
print(ans)