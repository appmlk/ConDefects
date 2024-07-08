n = int(input())
phrase = input().lower()
operation = int(input())

alphabet = "abcdefghijklmnopqrstuvwsyz"

for i in range(operation):
    original, become = input().split()
    alphabet = alphabet.replace(original, become)

new = ""

for ch in phrase:
    new += alphabet[ord(ch)-97]
print(new)
