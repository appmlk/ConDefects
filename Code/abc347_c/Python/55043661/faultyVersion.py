n, a, b = map(int, input().split())
D = list(map(int, input().split()))

w = a + b
mod_D = []
for d in D:
    mod_D.append(d % w)

mod_D = sorted(list(set(mod_D)))
length = len(mod_D)
for i in range(length):
    mod_D.append(mod_D[i] + w)

for i in range(len(mod_D)-1):
    if (mod_D[i+1] - mod_D[i]) % w > b:
        print("Yes")
        exit()
print("No")