N = int(input())
S = input()
d = S.split("C")
d = ["A" * (p.count("A") + p.count("B") // 2) + "B" * (p.count("B") % 2) for p in d]
print("C".join(d))