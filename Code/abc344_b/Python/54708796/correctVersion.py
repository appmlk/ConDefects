a_s = []
while True:
    a = int(input())
    a_s.append(a)
    if a == 0:
        break


for a in reversed(a_s):
    print(a)
