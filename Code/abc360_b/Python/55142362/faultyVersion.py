import math

s, t = input().split()
s, t = s.strip(), t.strip()

for w in range(1,len(s)):
    ss = [s[w*i:w*i+w] for i in range(math.ceil(len(s)/w))]
    ss[-1] += " " * (len(s) % w)
    x = [*zip(*ss)]
    for n in x:
        if t == "".join(n).strip():
            print("Yes")
            exit(0)
print("No")