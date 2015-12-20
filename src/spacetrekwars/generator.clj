(ns spacetrekwars.generator
  (:require [clojure.string :as str]))

(defmacro defrand
  [name & choices]
  `(defn ~name [] (rand-nth ~(vec choices))))

(defn a
  [string]
  (cond
    (re-find #"(?i)^(a|the) " string) string
    (#{\a \e \i \o \u} (first (.toLowerCase string))) (str "an " string)
    :else (str "a " string)))

(defn deprefix
  [string]
  (str/replace string #"(?i)^(an?|the) " ""))

(defrand spaceship-a
  "The Death"
  "The Millennial"
  "The Millennium"
  "The USS"
  "a Star"
  "a TIE"
  "an X-Wing")

(defrand spaceship-b
  "Cruiser"
  "Defiant"
  "Destroyer"
  "Enterprise"
  "Falcon"
  "Falcor"
  "Fighter"
  "Penguin"
  "Pigeon"
  "Star"
  "Voyager")

(defn spaceship [] (str (spaceship-a) " " (spaceship-b)))

(defrand rank
  "Ambassador"
  "Borg Queen"
  "Captain"
  "Chancellor"
  "Clone Commander"
  "Commander"
  "Doctor"
  "Emperor"
  "General"
  "Grand Moff"
  "Grand Nagus"
  "Jedi Apprentice"
  "Jedi Knight"
  "King"
  "Lieutenant"
  "Major"
  "President"
  "Prince"
  "Princess"
  "Queen"
  "Rear Admiral"
  "Senator"
  "Sith Lord")

(defrand first-name
  "Anakin"
  "Arya"
  "Boba"
  "C3PO"
  "Chakotay"
  "Chewbacca"
  "Darth"
  "Data"
  "Han"
  "Jabba"
  "James T."
  "Jar Jar"
  "Jean-Luc"
  "Kylo"
  "Lando"
  "Leia"
  "Leonard"
  "Luke"
  "Mace"
  "Mama"
  "Nyota"
  "Obi-Wan"
  "Padmé"
  "Pavel"
  "Poe"
  "Qui-Gon"
  "R2"
  "Rey"
  "Sansa"
  "Spock"
  "Tuvok"
  "Wesley"
  "William"
  "Yoda")

(defrand last-name
  "3PO"
  "Ackbar"
  "Amidala"
  "BB-8"
  "Binks"
  "Calrissian"
  "Chekov"
  "Chewbacca"
  "Crusher"
  "D2"
  "Data"
  "Dooku"
  "Fett"
  "Finn"
  "Grievous"
  "Janeway"
  "Jinn"
  "Kenobi"
  "Kirk"
  "Lanister"
  "Lowbacca"
  "Maul"
  "McCoy"
  "Millenial"
  "Organa"
  "Palpatine"
  "Picard"
  "Ren"
  "Riker"
  "Scott"
  "Sidious"
  "Skywalker"
  "Solo"
  "Spacewalker"
  "Spock"
  "Stark"
  "Starwalker"
  "Sulu"
  "Tyranus"
  "Uhura"
  "Vader"
  "Watto"
  "Windu"
  "Yoda"
  "of Borg"
  "the Hutt")

(defrand character
  (str (rank) " " (first-name) " " (last-name))
  (str (first-name) " " (last-name))
  (str (rank) " " (last-name))
  (first-name)
  (last-name)
  (str "The " (rank)))

(defrand weapon-material
  "Laser"
  "Light"
  "Ionic"
  "Power"
  "Phase"
  "Magic"
  "Force")

(defrand weapon-type
  "Sword"
  "Blaster"
  "Gun"
  "Stick"
  "Phaser"
  "Bomb"
  "Saber")

(defrand weapon
  (str (weapon-material) (.toLowerCase (weapon-type)))
  (str (weapon-material) " " (weapon-type))
  (str (weapon-material) "-" (weapon-type)))

(defrand body-part
  "arm"
  "elbow"
  "finger"
  "head"
  "kidney"
  "knee"
  "leg"
  "liver"
  "nose"
  "tentacle"
  "toe")

(defrand planet
  "Dagobah"
  "Earth"
  "Endor"
  "Gallifrey"
  "Hoth"
  "Jakku"
  "Kling"
  "Naboo"
  "Skaro"
  "Tatooine"
  "The Borg Homeworld"
  "The Death Star"
  "The Klingon Homeworld"
  "Vulcan"
  "Westeros")

(defrand possession
  (a (body-part))
  (a (weapon))
  (spaceship))

(defrand team
  "The Borg"
  "The Cybermen"
  "The Daleks"
  "The Dark Side"
  "The Empire"
  "The Federation"
  "The First Order"
  "The Humans"
  "The Jedis"
  "The Klingons"
  "The Lanisters"
  "The Light Side"
  "The Rebels"
  "The Sith"
  "The Time Lords"
  "The Vulcans"
  "The Time Lords")

(defrand action
  "annoys"
  "blows up"
  "borrows"
  "breaks"
  "decorates"
  "designs"
  "destroys"
  "explodes"
  "fixes"
  "hits"
  "hugs"
  "kicks"
  "kidnaps"
  "kills"
  "kisses"
  "paints"
  "pickles"
  "punches"
  "steals"
  "throws")

(defrand action-adjective
  "accidentally"
  "deliberately"
  "dramatically"
  "mischievously"
  "mistakenly"
  "naughtily")

(defrand character-action
  "falls in love"
  "makes a baby"
  "gets married"
  (str (action) " " (possession)))

(defrand who
  (character)
  (team)
  (str (character) " and " (character)))

(defrand spoiler-start
  ""
  "Apparently, "
  "Did you know, "
  "I heard that "
  "It turns out "
  "At the end, "
  "In the new film, ")

(defrand spoiler
  (str (spoiler-start) (character) " " (action) " " (character) " with " (a (weapon)))
  (str (spoiler-start) (character) " " (action-adjective) " " (character-action) " with " (character))
  (str (spoiler-start) (character) " " (action-adjective) " " (action) " " (character) "'s " (deprefix (possession)))
  (str (spoiler-start) (character) " " (action-adjective) " cuts " (character) "'s " (body-part) " off")
  (str (spoiler-start) (character) " " (action-adjective) " learns how to fly " (spaceship))
  (str (spoiler-start) (team) " invade " (planet))
  (str (spoiler-start) (character) " convinces " (character) " to join " (team))
  (str (spoiler-start) (character) " is secretly " (character)))

