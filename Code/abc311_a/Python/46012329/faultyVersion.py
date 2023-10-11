N = int(input())
S = input()

print(max([S.rfind('A'), S.rfind('B'), S.rfind('C')])+1)