# Contributing Guidelines

**Thanks for contributing to Powerum**!

To make contributing as smooth as possible, please follow these guidelines before submitting code or opening issues.

---

# Getting started & building

### Java & Gradle

- To program with Java, you need a **Java development kit**, known as **JDK** and an **IDE**. <br>
- We recommend to use [IntelliJ IDEA](https://www.jetbrains.com/idea/) with [Adoptium temurin](https://adoptium.net/).
- Gradle **doesn't need to be installed manually**, just use the included **Gradle Wrapper** (`./gradlew`).

---

### Fabric & Minecraft

- Fabric projects require the **Loom** plugin.

**Gradle Groovy DSL** (`build.gradle`)

```groovy
plugins {
    id 'net.fabricmc.fabric-loom' version "1.17-SNAPSHOT"
    id 'maven-publish'
}
```

**Gradle Kotlin DSL** (`build.gradle.kts`)

```kotlin
plugins {
	id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
	`maven-publish`
}
```

---

### Forks

In order to fork this project, See the [LICENSE](LICENSE) file and respect these rules.

---

### Copyright

<details> 
<summary> <b> This project is licensed under the GNU LGPLv3. You must retain all original copyright notices, keep the same license, and preserve author credits.</b> </summary>

```text 
  You may convey a Combined Work under terms of your choice that,
taken together, effectively do not restrict modification of the
portions of the Library contained in the Combined Work and reverse
engineering for debugging such modifications, if you also do each of
the following:

   a) Give prominent notice with each copy of the Combined Work that
   the Library is used in it and that the Library and its use are
   covered by this License.

   b) Accompany the Combined Work with a copy of the GNU GPL and this license
   document.

   c) For a Combined Work that displays copyright notices during
   execution, include the copyright notice for the Library among
   these notices, as well as a reference directing the user to the
   copies of the GNU GPL and this license document.

   d) Do one of the following:

       0) Convey the Minimal Corresponding Source under the terms of this
       License, and the Corresponding Application Code in a form
       suitable for, and under terms that permit, the user to
       recombine or relink the Application with a modified version of
       the Linked Version to produce a modified Combined Work, in the
       manner specified by section 6 of the GNU GPL for conveying
       Corresponding Source.

       1) Use a suitable shared library mechanism for linking with the
       Library.  A suitable mechanism is one that (a) uses at run time
       a copy of the Library already present on the user's computer
       system, and (b) will operate properly with a modified version
       of the Library that is interface-compatible with the Linked
       Version.

   e) Provide Installation Information, but only if you would otherwise
   be required to provide such information under section 6 of the
   GNU GPL, and only to the extent that such information is
   necessary to install and execute a modified version of the
   Combined Work produced by recombining or relinking the
   Application with a modified version of the Linked Version. (If
   you use option 4d0, the Installation Information must accompany
   the Minimal Corresponding Source and Corresponding Application
   Code. If you use option 4d1, you must provide the Installation
   Information in the manner specified by section 6 of the GNU GPL
   for conveying Corresponding Source.)
```

</details>

---

# Issues

Found a bug or experiencing a crash? **Follow these rules** before opening a new issue.

- **Spam**: If we don't reply immediately please don't spam.
- **Crash cause**: Crashes may be caused by compatibility issues, Some mods **may cause crash with Powerum**. Or all the dependencies are not installed.
- **Forks**: If an issue is caused on a **Fork of Powerum**, or on not original Minecraft, Contact the creator of the fork, not Powerum.