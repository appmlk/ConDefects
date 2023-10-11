N = int(input())
S = input()
d = S.split("C")
d = ["A" * p.count("A") + "B" * p.count("B") for p in d]
print("C".join(d))