a_s = []
while True:
    a = int(input())
    if a == 0:
        break
    a_s.append(a)

for a in reversed(a_s):
    print(a)
